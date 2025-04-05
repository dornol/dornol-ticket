"use client";

import { useParams, useRouter } from "next/navigation";
import SiteForm from "@/app/(authenticated)/sites/site-form";
import { useMutation, useQuery } from "@tanstack/react-query";
import siteService from "@/lib/service/site/site-service";
import { Dialog } from "@/lib/dialog/dialog";
import { ApiError } from "@/lib/types/common/api-error";
import { SiteAddRequestDto, SiteDto } from "@/lib/types/site/site.dto";

export default function SiteEditPage() {
  const { id }: { id: string } = useParams();
  const router = useRouter();

  const { data } = useQuery<SiteDto>({
    queryKey: ['site', id],
    queryFn: () => siteService.get(id)
  })

  const mutation = useMutation({
    mutationFn: (data: SiteAddRequestDto) => siteService.edit(id, data),
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
      {data && <SiteForm onSubmit={onSubmit} onCancel={goBack} site={data} mode="edit" />}
    </>
  )
}