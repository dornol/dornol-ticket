import apiClient from "@/lib/axios/api";
import { SiteAddRequestDto, SiteListDto } from "@/lib/types/site/site.dto";
import { PageImpl } from "@/lib/types/common/page";

const SITE_LIST_URL = "/sites"
const SITE_ADD_URL = "/sites";


class SiteService {

  async list(params: URLSearchParams): Promise<PageImpl<SiteListDto>> {
    const res = await apiClient.get(SITE_LIST_URL, {
      params: params
    });
    return res.data;
  }

  async add(data: SiteAddRequestDto): Promise<void> {
    return apiClient.post(SITE_ADD_URL, data)
  }

}

const siteService = new SiteService();
export default siteService;