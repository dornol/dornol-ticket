import { PageImpl } from "@/lib/types/common/page";
import { PerformanceListDto } from "@/lib/types/performance/performance.dto";
import apiClient from "@/lib/axios/api";

const PERFORMANCE_LIST_URL = '/performances';

class PerformanceService {

  async list(params: URLSearchParams): Promise<PageImpl<PerformanceListDto>> {
    const res = await apiClient.get(PERFORMANCE_LIST_URL, {
      params: params
    })
    return res.data;
  }

}

const performanceService = new PerformanceService();

export default performanceService;