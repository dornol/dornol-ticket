import { PageImpl } from "@/lib/types/common/page";
import {
  PerformanceAddRequestDto, PerformanceDetailDto,
  PerformanceEditRequestDto,
  PerformanceListDto
} from "@/lib/types/performance/performance.dto";
import apiClient from "@/lib/axios/api";

const PERFORMANCE_GET_URL = (id: string) => `/performances/${id}`;
const PERFORMANCE_LIST_URL = '/performances';
const PERFORMANCE_ADD_URL = '/performances';
const PERFORMANCE_EDIT_URL = (id: string) => `/performances/${id}`;

class PerformanceService {

  async get(id: string): Promise<PerformanceDetailDto> {
    return apiClient.get(PERFORMANCE_GET_URL(id)).then((data) => data.data);
  }

  async list(params: URLSearchParams): Promise<PageImpl<PerformanceListDto>> {
    const res = await apiClient.get(PERFORMANCE_LIST_URL, {
      params: params
    })
    return res.data;
  }

  async add(performance: PerformanceAddRequestDto): Promise<void> {
    return apiClient.post(PERFORMANCE_ADD_URL, performance);
  }

  async edit(id: string, data: PerformanceEditRequestDto) {
    return apiClient.put(PERFORMANCE_EDIT_URL(id), data);
  }
}

const performanceService = new PerformanceService();

export default performanceService;