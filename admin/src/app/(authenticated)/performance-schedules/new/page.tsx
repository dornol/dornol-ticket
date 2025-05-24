"use client";

import { useRouter } from "next/navigation";
import { useMutation } from "@tanstack/react-query";
import { Dialog } from "@/lib/dialog/dialog";
import { ApiError } from "@/lib/types/common/api-error";
import PerformanceScheduleAddForm from "@/app/(authenticated)/performance-schedules/performance-schedule-add-form";
import { PerformanceScheduleAddRequestDto } from "@/lib/types/performance-schedule/performance-schedule.dto";
import performanceScheduleService from "@/lib/service/performance-schedule/performance-schedule-service";

export default function Page() {
  const router = useRouter();
  const mutation = useMutation({
    mutationFn: performanceScheduleService.add,
    onSuccess: async () => {
      await Dialog.alert("저장되었습니다.");
      router.push('/performance-schedules');
    },
    onError: (error: ApiError) => {
      console.log(error);
    }
  })

  const goBack = () => {
    router.back();
  }

  const onSubmit = (data: PerformanceScheduleAddRequestDto) => {
    mutation.mutate(data);
  }

  return (
    <>
      <PerformanceScheduleAddForm onSubmitAction={onSubmit} onCancelAction={goBack} />
    </>
  )
}