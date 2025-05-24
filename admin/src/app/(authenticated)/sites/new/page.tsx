"use client";

import { SiteAddRequestDto } from "@/lib/types/site/site.dto";
import { useMutation } from "@tanstack/react-query";
import { Dialog } from "@/lib/dialog/dialog";
import { ApiError } from "@/lib/types/common/api-error";
import { useRouter } from "next/navigation";
import siteService from "@/lib/service/site/site-service";
import SiteForm from "@/app/(authenticated)/sites/site-form";

export default function SiteAddPage() {
  const router = useRouter();

  const mutation = useMutation({
    mutationFn: siteService.add,
    onSuccess: async () => {
      await Dialog.alert("저장되었습니다.");
      router.push('/sites');
    },
    onError: (error: ApiError) => {
      console.log(error);
    }
  })

  const onSubmit = (data: SiteAddRequestDto) => {
    mutation.mutate(data);
  };

  const goBack = () => {
    router.back();
  }

  return (
    <>
      <SiteForm onSubmitAction={onSubmit} onCancelAction={goBack} mode="add" />
    </>
  )
}