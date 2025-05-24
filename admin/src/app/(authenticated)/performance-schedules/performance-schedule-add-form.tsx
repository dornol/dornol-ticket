"use client";

import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { PerformanceScheduleAddRequestDto } from "@/lib/types/performance-schedule/performance-schedule.dto";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Button } from "@/components/ui/button";
import { useQuery } from "@tanstack/react-query";
import { PerformanceListDto } from "@/lib/types/performance/performance.dto";
import performanceService from "@/lib/service/performance/performance-service";
import { PageImpl } from "@/lib/types/common/page";
import siteService from "@/lib/service/site/site-service";
import { SiteListDto } from "@/lib/types/site/site.dto";

const schema = z.object({
  siteId: z.string()
    .nonempty({message: "장소를 선택해 주세요."}),
  performanceId: z.string()
    .nonempty({ message: "공연을 선택해 주세요." }),
  performanceDate: z.string()
    .nonempty({ message: "공연 날짜를 선택해 주세요." }),
  performanceTime: z.string()
    .nonempty({ message: "공연 시간을 선택해 주세요." }),
});

const siteQueryKey = "sites";
const performanceQueryKey = "performances";

export default function PerformanceScheduleAddForm({
  onSubmitAction,
  onCancelAction,
}: {
  onSubmitAction: (data: PerformanceScheduleAddRequestDto) => void;
  onCancelAction: () => void;
}) {
  const form = useForm<z.infer<typeof schema>>({
    resolver: zodResolver(schema),
    defaultValues: {
      siteId: "",
      performanceId: '',
      performanceDate: "",
      performanceTime: "",
    },
  });

  const { data: performanceData, isSuccess: isPerformancesSuccess } = useQuery<PageImpl<PerformanceListDto>>({
    queryKey: [performanceQueryKey],
    queryFn: () => performanceService.list(new URLSearchParams())
  })

  const { data: siteData, isSuccess: isSitesSuccess } = useQuery<PageImpl<SiteListDto>>({
    queryKey: [siteQueryKey],
    queryFn: () => siteService.list(new URLSearchParams())
  })

  if (!isPerformancesSuccess || !isSitesSuccess) {
    return null;
  }

  return (
    <>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmitAction)} className="space-y-8">

          <FormField
            control={form.control}
            name="performanceId"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Performance</FormLabel>
                <Select
                  onValueChange={field.onChange}
                  defaultValue={field.value}
                >
                  <FormControl>
                    <SelectTrigger>
                      <SelectValue placeholder="공연을 선택하세요." />
                    </SelectTrigger>
                  </FormControl>
                  <SelectContent>
                    {
                      performanceData.content.map((performance) => (
                        <SelectItem key={performance.id} value={performance.id}>{performance.name}</SelectItem>
                      ))
                    }
                  </SelectContent>
                </Select>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="siteId"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Site</FormLabel>
                <Select
                  onValueChange={field.onChange}
                  defaultValue={field.value}
                >
                  <FormControl>
                    <SelectTrigger>
                      <SelectValue placeholder="장소를 선택하세요." />
                    </SelectTrigger>
                  </FormControl>
                  <SelectContent>
                    {
                      siteData.content.map((site) => (
                        <SelectItem key={site.id} value={site.id}>{site.name}</SelectItem>
                      ))
                    }
                  </SelectContent>
                </Select>
                <FormMessage />
              </FormItem>
            )}
          />

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
                <FormLabel>Performance time</FormLabel>
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