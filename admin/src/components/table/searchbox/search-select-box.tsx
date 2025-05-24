"use client";

import { SearchOption } from "@/lib/types/search/search.dto";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { useState } from "react";

const unSelectedValue = "NOT-SELECTED";

export default function SearchSelectBox({
  title,
  name,
  options
}: SearchOption) {
  const [selectedValue, setSelectedValue] = useState<string | undefined>(undefined);

  const handleValueChange = (value: string) => {
    if (value === unSelectedValue) {
      setSelectedValue("");
      return;
    }
    setSelectedValue(value);
  }

  return (
    <Select name={name} value={selectedValue} onValueChange={handleValueChange}>
      <SelectTrigger className="w-[180px]">
        <SelectValue placeholder={title} />
      </SelectTrigger>
      <SelectContent>
        <SelectItem value={unSelectedValue}>ALL</SelectItem>
        {
          options?.map(option => (
            <SelectItem key={`${name}-${option.value}`} value={option.value ?? ""}>{option.title}</SelectItem>)
          )
        }
      </SelectContent>
    </Select>
  )
}