import { Address } from "@/lib/types/site/site.dto";

export interface PerformanceScheduleDTO {
  id: string;
  performance: {
    id: string;
    name: string;
    type: string;
  };
  site: {
    id: string;
    name: string;
    address: Address
  };
  date: string;
  time: string;
}