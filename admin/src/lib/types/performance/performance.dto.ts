import { Address } from "@/lib/types/site/site.dto";

export interface PerformanceListDto {
  id: string;
  name: string;
  type: string;
  site: {
    id: string;
    name: string;
    address: Address
  }
}