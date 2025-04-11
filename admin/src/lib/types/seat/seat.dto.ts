export interface SeatDto {
  id: string;
  name: string;
  displayOrder: number;
  groupId: string;
  offset: SeatOffset;
}

export interface SeatOffset {
  x: number;
  y: number;
}

export interface SeatGroup {
  id: string;
  name: string;
  color: string;
  displayOrder: number;
  seats: SeatDto[];
}

export interface SeatGroupAddRequest {
  name: string;
  color: string;
}

export interface SeatGroupEditRequest extends SeatGroupAddRequest{
  id: string;
}