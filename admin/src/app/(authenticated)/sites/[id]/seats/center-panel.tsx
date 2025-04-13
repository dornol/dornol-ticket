import { ContextMenu, ContextMenuContent, ContextMenuItem, ContextMenuTrigger } from "@/components/ui/context-menu";
import SeatItemBlock from "@/app/(authenticated)/sites/[id]/seats/seat-item-block";
import * as React from "react";
import { useState } from "react";
import { SeatDto, SeatGroup, SeatOffset } from "@/lib/types/seat/seat.dto";
import { SiteDto } from "@/lib/types/site/site.dto";
import { QueryObserverResult, useMutation } from "@tanstack/react-query";
import seatService from "@/lib/service/seat/seat-service";

export default function CenterPanel({
  site,
  seats,
  seatGroups,
  groupMap,
  selectedSeatId,
  setSelectedSeatId,
  refetch,
}: {
  site?: SiteDto;
  seats: SeatDto[];
  seatGroups?: SeatGroup[];
  groupMap: Record<string, SeatGroup>;
  selectedSeatId: string | null;
  setSelectedSeatId: (seatId: string) => void;
  refetch: () => Promise<QueryObserverResult<SeatGroup[], Error>>;
}) {
  const [contextPosition, setContextPosition] = useState<SeatOffset>({ x: 0, y: 0 });
  const siteId = site?.id ?? "";

  const { mutate: SeatAddMutate } = useMutation({
    mutationFn: ({ seatGroup, offset }: {
      seatGroup: SeatGroup,
      offset: SeatOffset
    }) => seatService.addSeat(siteId, seatGroup.id, offset),
    onSuccess: refetch
  })

  const { mutate: seatMoveMutate } = useMutation({
    mutationFn: ({ seat, offset }: {
      seat: SeatDto,
      offset: SeatOffset
    }) => seatService.moveSeat(siteId, seat.groupId, seat.id, offset),
    onSuccess: refetch
  })

  /* ───────── drag stop → 상태 + 백엔드 저장 ───────── */
  const handleDragStop = (seat: SeatDto, x: number, y: number) => {
    seatMoveMutate({ seat, offset: { x, y } })
  }

  return (
    <>
      <ContextMenu>
        {/* 👇 이 div는 오직 스크롤만 담당 */}
        <ContextMenuTrigger className="relative flex-1 overflow-auto" onContextMenu={(ev) => {
          const rect = ev.currentTarget.getBoundingClientRect();
          const relativeX = ev.clientX - rect.left + ev.currentTarget.scrollLeft;
          const relativeY = ev.clientY - rect.top + ev.currentTarget.scrollTop;

          setContextPosition({
            x: relativeX,
            y: relativeY
          })
        }}>
          {/* 👇 이 div에 배경 이미지 적용 → 좌석과 함께 스크롤됨 */}
          <div
            className="relative w-[1200px] h-[1200px] max-w-[1200px] max-h-[1200px] bg-no-repeat bg-top bg-contain"
            style={{
              backgroundImage: `url(${site?.seatingMapLocation})`,
              backgroundSize: '100% 100%',
            }}
          >
            {/* 좌석들 */}
            {seats.map((seat) => (
              <SeatItemBlock
                key={seat.id}
                seat={seat}
                color={groupMap[seat.groupId]?.color ?? '#FFF'}
                onSelect={(seat) => setSelectedSeatId(seat.id)} onMove={handleDragStop}
                active={selectedSeatId === seat.id}
              />
            ))}
          </div>
        </ContextMenuTrigger>
        <ContextMenuContent className="w-64">
          {
            seatGroups?.map(group => (
              <ContextMenuItem
                key={group.id}
                onClick={() => {
                  SeatAddMutate({
                    seatGroup: group,
                    offset: contextPosition
                  })
                }}
                inset
              >
                <div className="w-[10px] h-[10px]" style={{backgroundColor: group.color}}></div>
                <p>Add {group.name} seat</p>
              </ContextMenuItem>
            ))
          }
        </ContextMenuContent>
      </ContextMenu>
    </>
  )
}