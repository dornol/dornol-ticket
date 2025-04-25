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

export interface PerformanceDetailDto {
  id: string;
  name: string;
  type: string;
  siteId: string;
}

export interface PerformanceAddRequestDto {
  name: string;
  type: string;
  siteId?: string;
}

export interface PerformanceEditRequestDto {
  name: string;
  type: string;
}