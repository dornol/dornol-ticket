import apiClient from "@/lib/axios/api";
import { SeatGroup, SeatGroupAddRequest } from "@/lib/types/seat/seat.dto";


const SEAT_GROUP_URL = (siteId: string) => `/sites/${siteId}/seat-groups`;
const SEAT_GROUP_ADD_URL = (siteId: string) => `/sites/${siteId}/seat-groups`;
const SEAT_GROUP_EDIT_URL = (siteId: string, seatGroupId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}`;
const SEAT_GROUP_DELETE_URL = (siteId: string, seatGroupId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}`;

class SeatService {

  async getSeatGroups(siteId: string): Promise<SeatGroup[]> {
    const res = await apiClient.get(SEAT_GROUP_URL(siteId));
    return res.data;
  }

  async addSeatGroup(siteId: string, seatGroup: SeatGroupAddRequest): Promise<void> {
    return apiClient.post(SEAT_GROUP_ADD_URL(siteId), seatGroup);
  }

  async editSeatGroup(siteId: string, seatGroupId: string, seatGroup: SeatGroupAddRequest): Promise<void> {
    return apiClient.put(SEAT_GROUP_EDIT_URL(siteId, seatGroupId), seatGroup);
  }

  async deleteSeatGroup(siteId: string, seatGroupId: string) {
    return apiClient.delete(SEAT_GROUP_DELETE_URL(siteId, seatGroupId));
  }

}

const seatService = new SeatService();
export default seatService;