"use client";

import {z} from "zod";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {PerformanceAddRequestDto, PerformanceDetailDto} from "@/lib/types/performance/performance.dto";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";

const schema = z.object({
  name: z.string()
    .min(2, { message: "이름은 최소 2자 이상이어야 합니다.", })
    .max(50, { message: "이름은 50자 이하여야 합니다.", }),
  type: z.string()
    .nonempty({ message: '공연 유형을 선택해 주세요.' }),
});

export default function PerformanceForm({
  performance,
  onSubmit,
  onCancel,
}: {
  performance?: PerformanceDetailDto
  onSubmit: (data: PerformanceAddRequestDto) => void;
  onCancel: () => void;
}) {
  const form = useForm<z.infer<typeof schema>>({
    resolver: zodResolver(schema),
    defaultValues: {
      name: "",
      type: '',
      ...performance
    },
  });

  return (
    <>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
          <FormField
            control={form.control}
            name="name"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Performance name</FormLabel>
                <FormControl>
                  <Input placeholder="공연 이름을 입력하세요" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          <FormField
            control={form.control}
            name="type"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Performance Type</FormLabel>
                <Select
                  onValueChange={field.onChange}
                  defaultValue={field.value}
                >
                  <FormControl>
                    <SelectTrigger>
                      <SelectValue placeholder="공연 종류를 선택하세요" />
                    </SelectTrigger>
                  </FormControl>
                  <SelectContent>
                    <SelectItem value="PERFORMANCE">공연</SelectItem>
                    <SelectItem value="MOVIE">영화</SelectItem>
                    <SelectItem value="CONCERT">콘서트</SelectItem>
                  </SelectContent>
                </Select>
                <FormMessage />
              </FormItem>
            )}
          />

          {/* 제출 버튼 */}
          <Button type="submit">Save</Button>
          <Button type="button" variant="secondary" onClick={onCancel}>Cancel</Button>
        </form>
      </Form>
    </>
  )
}