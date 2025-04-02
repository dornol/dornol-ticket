export interface SiteListDto {
  id: string;
  name: string;
  address: Address;
  companyId: string;
}

export interface Address {
  mainAddress: string;
  detailAddress: string;
  zipcode: string;
}

export interface SiteAddRequestDto {
  name: string;
  address: Address;
}