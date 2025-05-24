"use client";

import { useParams, useRouter } from "next/navigation";
import { useMutation, useQuery } from "@tanstack/react-query";
import { Dialog } from "@/lib/dialog/dialog";
import { ApiError } from "@/lib/types/common/api-error";
import {
  PerformanceScheduleDto,
  PerformanceScheduleEditRequestDto
} from "@/lib/types/performance-schedule/performance-schedule.dto";
import performanceScheduleService from "@/lib/service/performance-schedule/performance-schedule-service";
import PerformanceScheduleEditForm from "@/app/(authenticated)/performance-schedules/performance-schedule-edit-form";

const queryKey = 'performanceSchedule';

export default function Page() {
  const { id }: { id: string } = useParams();
  const router = useRouter();

  const { data, isSuccess } = useQuery<PerformanceScheduleDto>({
    queryKey: [queryKey, id],
    queryFn: () => performanceScheduleService.get(id)
  })

  const mutation = useMutation({
    mutationFn: (data: PerformanceScheduleEditRequestDto) => performanceScheduleService.edit(id, data),
    onSuccess: async () => {
      await Dialog.alert("저장되었습니다.");
      router.back();
    },
    onError: (error: ApiError) => {
      console.log(error);
    }
  })

  const onSubmit = async (data: PerformanceScheduleEditRequestDto) => {
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
      <PerformanceScheduleEditForm onSubmitAction={onSubmit} onCancelAction={goBack} performanceSchedule={data} />
    </>
  )
}