import { ReactNode } from "react";
import { ArrowUpDown } from "lucide-react";
import { Button } from "@/components/ui/button";
import { HeaderContext } from "@tanstack/react-table";

export default function SortableHeader<TData, TValue>({children, context}: {children: ReactNode, context: HeaderContext<TData, TValue>}) {
  return (
    <Button
      variant="ghost"
      onClick={() => context.column.toggleSorting(context.column.getIsSorted() === "asc")}
    >
      {children}
      <ArrowUpDown className="ml-2 h-4 w-4" />
    </Button>
  )
}