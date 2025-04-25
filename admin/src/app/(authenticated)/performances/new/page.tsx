"use client";

import PerformanceForm from "@/app/(authenticated)/performances/performance-form";
import { useRouter } from "next/navigation";
import { useMutation } from "@tanstack/react-query";
import { Dialog } from "@/lib/dialog/dialog";
import { ApiError } from "@/lib/types/common/api-error";
import performanceService from "@/lib/service/performance/performance-service";
import { PerformanceAddRequestDto } from "@/lib/types/performance/performance.dto";

export default function Page() {
  const router = useRouter();
  const mutation = useMutation({
    mutationFn: performanceService.add,
    onSuccess: async () => {
      await Dialog.alert("저장되었습니다.");
      router.push('/performances');
    },
    onError: (error: ApiError) => {
      console.log(error);
    }
  })

  const goBack = () => {
    router.back();
  }

  const onSubmit = (data: PerformanceAddRequestDto) => {
    mutation.mutate(data);
  }

  return (
    <>
      <PerformanceForm mode='add' onSubmit={onSubmit} onCancel={goBack} />
    </>
  )
}