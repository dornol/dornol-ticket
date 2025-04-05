import apiClient from "@/lib/axios/api";
import { SiteAddRequestDto, SiteDto, SiteListDto } from "@/lib/types/site/site.dto";
import { PageImpl } from "@/lib/types/common/page";

const SITE_LIST_URL = "/sites"
const SITE_GET_URL = (id: string) => `/sites/${id}`;
const SITE_ADD_URL = "/sites";
const SITE_EDIT_URL = (id: string) => `/sites/${id}/edit`;


class SiteService {

  async get(id: string): Promise<SiteDto> {
    const res = await apiClient.get(SITE_GET_URL(id));
    return res.data;
  }

  async list(params: URLSearchParams): Promise<PageImpl<SiteListDto>> {
    const res = await apiClient.get(SITE_LIST_URL, {
      params: params
    });
    return res.data;
  }

  async add(data: SiteAddRequestDto): Promise<void> {
    return apiClient.post(SITE_ADD_URL, data);
  }

  async edit(id: string, data: SiteAddRequestDto): Promise<void> {
    return apiClient.put(SITE_EDIT_URL(id), data);
  }

}

const siteService = new SiteService();
export default siteService;