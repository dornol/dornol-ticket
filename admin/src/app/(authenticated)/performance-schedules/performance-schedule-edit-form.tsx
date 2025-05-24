"use client";

import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  PerformanceScheduleDto,
  PerformanceScheduleEditRequestDto
} from "@/lib/types/performance-schedule/performance-schedule.dto";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

const schema = z.object({
  performanceDate: z.string()
    .nonempty({ message: "공연 날짜를 선택해 주세요." }),
  performanceTime: z.string()
    .nonempty({ message: "공연 시간을 선택해 주세요." }),
});

export default function PerformanceScheduleEditForm({
  performanceSchedule,
  onSubmitAction,
  onCancelAction,
}: {
  performanceSchedule: PerformanceScheduleDto
  onSubmitAction: (data: PerformanceScheduleEditRequestDto) => void;
  onCancelAction: () => void;
}) {
  const form = useForm<z.infer<typeof schema>>({
    resolver: zodResolver(schema),
    defaultValues: {
      performanceDate: performanceSchedule.date,
      performanceTime: performanceSchedule.time,
    },
  });

  return (
    <>
      <Form {...form}>

        <form onSubmit={form.handleSubmit(onSubmitAction)} className="space-y-8">
          <input type="hidden" name="" />

          <FormItem>
            <FormLabel>Performance</FormLabel>
            <p>{performanceSchedule.performance.name}</p>
          </FormItem>

          <FormItem>
            <FormLabel>Site</FormLabel>
            <p>{performanceSchedule.site.name}</p>
          </FormItem>

          <FormField
            control={form.control}
            name="performanceDate"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Performance date</FormLabel>
                <FormControl>
                  <Input type="date" placeholder="공연 날짜를 선택하세요." {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="performanceTime"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Performance date</FormLabel>
                <FormControl>
                  <Input type="time" step="60" min="00:00" max="23:50" placeholder="공연 시작시간을 선택하세요." {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          {/* 제출 버튼 */}
          <Button type="submit">Save</Button>
          <Button type="button" variant="secondary" onClick={onCancelAction}>Cancel</Button>
        </form>
      </Form>
    </>
  )
}