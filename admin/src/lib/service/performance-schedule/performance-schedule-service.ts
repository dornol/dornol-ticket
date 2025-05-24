import { PageImpl } from "@/lib/types/common/page";
import apiClient from "@/lib/axios/api";
import {
  PerformanceScheduleAddRequestDto,
  PerformanceScheduleDto,
  PerformanceScheduleEditRequestDto
} from "@/lib/types/performance-schedule/performance-schedule.dto";

const SCHEDULE_GET_URL = (id: string) => `/performance-schedules/${id}`;
const SCHEDULE_LIST_URL = '/performance-schedules';
const SCHEDULE_ADD_URL = '/performance-schedules';
const SCHEDULE_EDIT_URL = (id: string) => `/performance-schedules/${id}`;

class PerformanceScheduleService {

  async get(id: string): Promise<PerformanceScheduleDto> {
    return apiClient.get(SCHEDULE_GET_URL(id)).then((data) => data.data);
  }

  async list(params: URLSearchParams): Promise<PageImpl<PerformanceScheduleDto>> {
    const res = await apiClient.get(SCHEDULE_LIST_URL, {
      params: params
    })
    return res.data;
  }

  async add(performance: PerformanceScheduleAddRequestDto): Promise<void> {
    return apiClient.post(SCHEDULE_ADD_URL, performance);
  }

  async edit(id: string, data: PerformanceScheduleEditRequestDto) {
    return apiClient.patch(SCHEDULE_EDIT_URL(id), data);
  }
}

const performanceScheduleService = new PerformanceScheduleService();

export default performanceScheduleService;