
class FileProvideService {

  getFileViewUrl(uuid: string) {
    return `${process.env.NEXT_PUBLIC_API_URL}/files/view/${uuid}`;
  }

  getFileDownloadUrl(uuid: string) {
    return `${process.env.NEXT_PUBLIC_API_URL}/files/download/${uuid}`;
  }

}

const fileProvideService = new FileProvideService()
export default fileProvideService;