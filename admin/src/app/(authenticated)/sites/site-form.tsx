"use client";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Drawer, DrawerContent, DrawerDescription, DrawerHeader, DrawerTitle, } from "@/components/ui/drawer"

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { z } from "zod";
import { useState } from "react";
import DaumPostcodeEmbed from "react-daum-postcode";
import { VisuallyHidden } from "@radix-ui/react-visually-hidden";
import { Address as DaumAddress } from "react-daum-postcode/lib/loadPostcode";
import { SiteAddRequestDto, SiteDto } from "@/lib/types/site/site.dto";
import FileFormItem from "@/components/common/file-form-item";
import { FileUploadResponseDto } from "@/lib/types/file/file.dto";

const siteAddFormSchema = z.object({
  name: z.string().min(2, {
    message: "이름은 최소 2자 이상이어야 합니다.",
  }),
  address: z.object({
    zipCode: z.string()
      .nonempty({ message: "주소를 선택해 주세요." })
      .length(5, { message: "정확한 우편번호를 입력해 주세요." })
      .regex(/^[0-9]{5}$/, { message: "정확한 우편번호를 입력해 주세요." }),
    mainAddress: z.string()
      .nonempty({ message: "주소를 선택해 주세요." })
      .max(150, { message: "올바른 주소를 선택해 주세요." }),
    detailAddress: z.string()
      .nonempty({ message: "상세주소를 입력해 주세요." })
      .max(150, { message: "올바른 상세주소를 입력해 주세요 ." })
  }),
  seatingMapFileId: z.string()
    .nonempty({ message: "좌석 배치도를 업로드해주세요." })
});

const siteEditFormSchema = z.object({
  name: z.string().min(2, {
    message: "이름은 최소 2자 이상이어야 합니다.",
  }),
  address: z.object({
    zipCode: z.string()
      .nonempty({ message: "주소를 선택해 주세요." })
      .length(5, { message: "정확한 우편번호를 입력해 주세요." })
      .regex(/^[0-9]{5}$/, { message: "정확한 우편번호를 입력해 주세요." }),
    mainAddress: z.string()
      .nonempty({ message: "주소를 선택해 주세요." })
      .max(150, { message: "올바른 주소를 선택해 주세요." }),
    detailAddress: z.string()
      .nonempty({ message: "상세주소를 입력해 주세요." })
      .max(150, { message: "올바른 상세주소를 입력해 주세요 ." })
  }),
  seatingMapFileId: z.string()
});

export default function SiteForm({
  site,
  onSubmit,
  onCancel,
  mode,
}: {
  site?: SiteDto;
  onSubmit: (data: SiteAddRequestDto) => void;
  onCancel: () => void;
  mode: "add" | "edit"
}) {
  const schema = mode === "add" ? siteAddFormSchema : siteEditFormSchema;
  const [drawerOpen, setDrawerOpen] = useState<boolean>(false)
  const form = useForm<z.infer<typeof schema>>({
    resolver: zodResolver(schema),
    defaultValues: {
      name: "",
      address: {
        zipCode: "",
        mainAddress: "",
        detailAddress: "",
      },
      seatingMapFileId: "",
      ...site,
    },
  });

  const onAddressSearchComplete = (address: DaumAddress) => {
    setDrawerOpen(false);
    form.setValue("address.zipCode", address.zonecode);
    form.setValue("address.mainAddress", address.address);
    form.setValue("address.detailAddress", '');
    form.setFocus("address.detailAddress");
  }

  const onFileUpload = (data: FileUploadResponseDto) => {
    form.setValue("seatingMapFileId", data.id);
  }

  return (
    <>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
          {/* 사용자 이름 필드 */}
          <FormField
            control={form.control}
            name="name"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Site name</FormLabel>
                <FormControl>
                  <Input placeholder="이름을 입력하세요" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="address.zipCode"
            render={({ field }) => (
              <FormItem>
                <FormLabel>
                  <span>Address zipcode</span>
                  <Button type="button" size="sm" variant="outline" onClick={() => setDrawerOpen(true)}>주소검색</Button>
                </FormLabel>
                <FormControl>
                  <Input placeholder="주소를 선택해 주세요." {...field} readOnly />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="address.mainAddress"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Address</FormLabel>
                <FormControl>
                  <Input placeholder="주소를 선택해 주세요." {...field} readOnly />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="address.detailAddress"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Address detail</FormLabel>
                <FormControl>
                  <Input placeholder="상세주소를 입력해 주세요." {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="seatingMapFileId"
            render={() => (
              <FileFormItem accept="image/*" onUpload={onFileUpload} defaultLocation={site?.seatingMapLocation} />
            )}
          />

          {/* 제출 버튼 */}
          <Button type="submit">Save</Button>
          <Button type="button" variant="secondary" onClick={onCancel}>Cancel</Button>
        </form>
      </Form>
      <Drawer open={drawerOpen} onOpenChange={setDrawerOpen} direction="top">
        <DrawerContent>
          <div className="mx-auto w-full">
            <VisuallyHidden>
              <DrawerHeader>
                <DrawerTitle>Search for an Address</DrawerTitle>
                <DrawerDescription>Find and select the location of your site</DrawerDescription>
              </DrawerHeader>
            </VisuallyHidden>
            <DaumPostcodeEmbed
              style={{ width: '100%' }}
              onComplete={onAddressSearchComplete}
            ></DaumPostcodeEmbed>
          </div>
        </DrawerContent>
      </Drawer>
    </>
  )
}