import apiClient from "@/lib/axios/api";
import { SeatGroup, SeatGroupAddRequest, SeatOffset } from "@/lib/types/seat/seat.dto";
import { SeatEditRequestDto } from "@/lib/types/site/site.dto";


const SEAT_GROUP_URL = (siteId: string) => `/sites/${siteId}/seat-groups`;
const SEAT_GROUP_ADD_URL = (siteId: string) => `/sites/${siteId}/seat-groups`;
const SEAT_GROUP_EDIT_URL = (siteId: string, seatGroupId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}`;
const SEAT_GROUP_DELETE_URL = (siteId: string, seatGroupId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}`;

const SEAT_ADD_URL = (siteId: string, seatGroupId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}/seats`;
const SEAT_DUPLICATE_URL = (siteId: string, seatGroupId: string, seatId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}/seats/${seatId}/duplicate`;
const SEAT_EDIT_URL = (siteId: string, seatGroupId: string, seatId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}/seats/${seatId}`;
const SEAT_DELETE_URL = (siteId: string, seatGroupId: string, seatId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}/seats/${seatId}`;
const SEAT_MOVE_URL = (siteId: string, seatGroupId: string, seatId: string) => `/sites/${siteId}/seat-groups/${seatGroupId}/seats/${seatId}/move`;

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


  async addSeat(siteId: string, seatGroupId: string, offset: SeatOffset) {
    return apiClient.post(SEAT_ADD_URL(siteId, seatGroupId), offset);
  }

  async duplicateSeat(siteId: string, seatGroupId: string, seatId: string) {
    return apiClient.post(SEAT_DUPLICATE_URL(siteId, seatGroupId, seatId));
  }

  async editSeat(siteId: string, seatGroupId: string, seatId: string, dto: SeatEditRequestDto) {
    return apiClient.put(SEAT_EDIT_URL(siteId, seatGroupId, seatId), dto);
  }

  async deleteSeat(siteId: string, seatGroupId: string, seatId: string) {
    return apiClient.delete(SEAT_DELETE_URL(siteId, seatGroupId, seatId));
  }

  async moveSeat(siteId: string, seatGroupId: string, seatId: string, offset: SeatOffset) {
    return apiClient.put(SEAT_MOVE_URL(siteId, seatGroupId, seatId), offset);
  }

}

const seatService = new SeatService();
export default seatService;