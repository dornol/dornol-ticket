"use client";

import PerformanceForm from "@/app/(authenticated)/performances/performance-form";
import { useParams, useRouter } from "next/navigation";
import { useMutation, useQuery } from "@tanstack/react-query";
import performanceService from "@/lib/service/performance/performance-service";
import { Dialog } from "@/lib/dialog/dialog";
import { ApiError } from "@/lib/types/common/api-error";
import { PerformanceDetailDto, PerformanceEditRequestDto } from "@/lib/types/performance/performance.dto";

const queryKey = 'performance';

export default function Page() {
  const { id }: { id: string } = useParams();
  const router = useRouter();

  const { data, isSuccess } = useQuery<PerformanceDetailDto>({
    queryKey: [queryKey, id],
    queryFn: () => performanceService.get(id)
  })

  const mutation = useMutation({
    mutationFn: (data: PerformanceEditRequestDto) => performanceService.edit(id, data),
    onSuccess: async () => {
      await Dialog.alert("저장되었습니다.");
      router.back();
    },
    onError: (error: ApiError) => {
      console.log(error);
    }
  })

  const onSubmit = async (data: PerformanceEditRequestDto) => {
    mutation.mutate(data);
  }

  const goBack = () => {
    router.back();
  }

  if (!isSuccess) {
    return;
  }

  return (
    <>
      <PerformanceForm performance={data} onSubmitAction={onSubmit} onCancelAction={goBack} />
    </>
  )
}