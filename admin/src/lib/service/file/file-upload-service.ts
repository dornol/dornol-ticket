import apiClient from "@/lib/axios/api";
import { FileUploadResponseDto } from "@/lib/types/file/file.dto";

const FILE_UPLOAD_URL = "/files"


class FileUploadService {

  async upload(file: File): Promise<FileUploadResponseDto> {
    const formData = new FormData();
    formData.append("file", file);
    const res = await apiClient.post(FILE_UPLOAD_URL, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    });
    return res.data;
  }

}

const fileUploadService = new FileUploadService();
export default fileUploadService;