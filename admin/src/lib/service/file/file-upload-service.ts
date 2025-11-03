import apiClient from "@/lib/axios/api";
import { FileUploadResponseDto } from "@/lib/types/file/file.dto";

const FILE_UPLOAD_URL = (bucket: string) => `/files/${bucket}`


class FileUploadService {

  async upload(bucket: string, file: File): Promise<FileUploadResponseDto> {
    const formData = new FormData();
    formData.append("file", file);
    return apiClient.post(FILE_UPLOAD_URL(bucket), formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    })
      .then((response) => response.data)
      .catch((e) => {
        console.log(e)
      });
  }

}

const fileUploadService = new FileUploadService();
export default fileUploadService;