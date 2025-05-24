import { Address } from "@/lib/types/site/site.dto";

export interface PerformanceScheduleDto {
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

export interface PerformanceScheduleAddRequestDto {
  siteId: string;
  performanceId: string;
  /**
   * yyyy-MM-dd
   */
  performanceDate: string;
  /**
   * HH:mm
   */
  performanceTime: string;
}

export interface PerformanceScheduleEditRequestDto {
  /**
   * yyyy-MM-dd
   */
  performanceDate: string;
  /**
   * HH:mm
   */
  performanceTime: string;
}