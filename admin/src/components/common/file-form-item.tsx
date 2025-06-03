"use client";

import { FormControl, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import fileUploadService from "@/lib/service/file/file-upload-service";
import { FileUploadResponseDto } from "@/lib/types/file/file.dto";
import { useState } from "react";
import { Dialog } from "@/lib/dialog/dialog";
import Lightbox from "yet-another-react-lightbox";

import "yet-another-react-lightbox/styles.css";
import Image from "next/image";
import fileProvideService from "@/lib/service/file/file-provide-service";

export default function FileFormItem({
  accept = "image/*",
  onUpload,
  defaultLocation,
}: {
  accept: string;
  onUpload: (data: FileUploadResponseDto) => void;
  defaultLocation?: string;
}) {
  const [uploading, setUploading] = useState<boolean>(false);
  const [previewUrl, setPreviewUrl] = useState<string | undefined>(defaultLocation);
  const [open, setOpen] = useState(false)

  const handleFileUpload = async (file: File) => {
    setUploading(true);

    const data = await fileUploadService.upload('site', file)

    if (data?.uuid) {
      onUpload(data)
      setPreviewUrl(fileProvideService.getFileViewUrl(data.uuid)); // optional: 미리보기용 URL
    } else {
      Dialog.alert("파일 업로드 실패");
    }
    setUploading(false);
  };

  return (
    <>
      <FormItem>
        <FormLabel>Seating map</FormLabel>
        <FormControl>
          <Input
            type="file"
            accept={accept}
            onChange={(e) => {
              const file = e.target.files?.[0];
              if (file) {
                handleFileUpload(file);
              }
            }}
          />
        </FormControl>
        {uploading && <p>업로드 중...</p>}
        {previewUrl && (
          <>
            <Image src={previewUrl} alt="미리보기" className="h-40 mt-2" onClick={() => setOpen(true)} width={150} height={150} />
            <Lightbox
              open={open}
              close={() => setOpen(false)}
              slides={[{ src: previewUrl }]}
            />
          </>

        )}
        <FormMessage />
      </FormItem>
    </>
  )
}