import { PageImpl } from "@/lib/types/common/page";
import { PerformanceAddRequestDto, PerformanceEditRequestDto } from "@/lib/types/performance/performance.dto";
import apiClient from "@/lib/axios/api";
import { PerformanceScheduleDTO } from "@/lib/types/performance-schedule/performance-schedule.dto";

const SCHEDULE_GET_URL = (id: string) => `/performance-schedules/${id}`;
const SCHEDULE_LIST_URL = '/performance-schedules';
const SCHEDULE_ADD_URL = '/performance-schedules';
const SCHEDULE_EDIT_URL = (id: string) => `/performance-schedules/${id}`;

class PerformanceScheduleService {

  async get(id: string): Promise<PerformanceScheduleDTO> {
    return apiClient.get(SCHEDULE_GET_URL(id)).then((data) => data.data);
  }

  async list(params: URLSearchParams): Promise<PageImpl<PerformanceScheduleDTO>> {
    const res = await apiClient.get(SCHEDULE_LIST_URL, {
      params: params
    })
    return res.data;
  }

  async add(performance: PerformanceAddRequestDto): Promise<void> {
    return apiClient.post(SCHEDULE_ADD_URL, performance);
  }

  async edit(id: string, data: PerformanceEditRequestDto) {
    return apiClient.put(SCHEDULE_EDIT_URL(id), data);
  }
}

const performanceScheduleService = new PerformanceScheduleService();

export default performanceScheduleService;