export interface SiteListDto {
  id: string;
  name: string;
  address: Address;
  companyId: string;
}

export interface Address {
  mainAddress: string;
  detailAddress: string;
  zipCode: string;
}

export interface SiteAddRequestDto {
  name: string;
  address: Address;
  seatingMapFileId?: string;
}

export interface SiteDto {
  id: string;
  name: string;
  address: Address;
  seatingMapLocation: string;
}

export interface SeatEditRequestDto {
  name: string;
  groupId: string;
}