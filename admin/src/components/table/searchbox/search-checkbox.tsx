import { SearchOption } from "@/lib/types/search/search.dto";
import { Checkbox } from "@/components/ui/checkbox";

export default function SearchCheckbox({
  title,
  name,
}: SearchOption) {
  const id = `search-checkbox-${name}`;
  return (
    <div className="flex items-center space-x-2">
      <Checkbox id={id} name={name} />
      <label
        htmlFor={id}
        className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
      >
        {title}
      </label>
    </div>
  )
}