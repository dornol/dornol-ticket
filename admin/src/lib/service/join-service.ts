import auth from "@/lib/axios/auth";
import { JoinRequest, UsernameCheckResponse } from "@/lib/types/join/join.dto";

const JOIN_URL = "/join";
const CHECK_USERNAME_URL = "/join/check-username"


class JoinService {

  async join(data: JoinRequest): Promise<void> {
    await auth.post(JOIN_URL, data);
  }

  async checkUsername(username: string): Promise<boolean> {
    const response = await auth.get<UsernameCheckResponse>(`${CHECK_USERNAME_URL}?username=${encodeURIComponent(username)}`);
    return response.data.available;
  }

}

const joinService = new JoinService();
export default joinService;