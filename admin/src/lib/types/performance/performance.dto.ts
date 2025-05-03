export interface PerformanceListDto {
  id: string;
  name: string;
  type: string;
}

export interface PerformanceDetailDto {
  id: string;
  name: string;
  type: string;
}

export interface PerformanceAddRequestDto {
  name: string;
  type: string;
}

export interface PerformanceEditRequestDto {
  name: string;
  type: string;
}