import { DatePickerWithRange } from "@/components/common/date-picker-with-range";
import { SearchOption } from "@/lib/types/search/search.dto";

export default function SearchRangePicker({
  title,
  name,
}: SearchOption) {
  return (
    <>
      <DatePickerWithRange title={title} name={name} />
    </>
  )
}