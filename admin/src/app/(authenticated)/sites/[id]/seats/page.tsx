"use client"

import * as React from "react"
import { useMemo, useState } from "react"
import { ResizableHandle, ResizablePanel, ResizablePanelGroup, } from "@/components/ui/resizable"
import { useParams } from "next/navigation";
import { useMutation, useQuery } from "@tanstack/react-query";
import { SeatEditRequestDto, SiteDto } from "@/lib/types/site/site.dto";
import siteService from "@/lib/service/site/site-service";
import seatService from "@/lib/service/seat/seat-service";
import { SeatDto, SeatGroup } from "@/lib/types/seat/seat.dto";
import SeatPropertiesArea from "@/app/(authenticated)/sites/[id]/seats/seat-properties-area";
import LeftSidePanel from "@/app/(authenticated)/sites/[id]/seats/left-side-panel";
import CenterPanel from "@/app/(authenticated)/sites/[id]/seats/center-panel";

const queryKey = 'site';
const seatGroupQueryKey = 'seat-group'

export default function SeatsPage() {
  const { id: siteId }: { id: string } = useParams();

  const { data } = useQuery<SiteDto>({
    queryKey: [queryKey, siteId],
    queryFn: () => siteService.get(siteId)
  })

  const { data: seatGroups, refetch: refetchSeatGroups } = useQuery<SeatGroup[]>({
    queryKey: [seatGroupQueryKey, siteId],
    queryFn: () => seatService.getSeatGroups(siteId)
  })

  const { mutate: duplicateSeatMutate } = useMutation({
    mutationFn: (seat: SeatDto) => seatService.duplicateSeat(siteId, seat.groupId, seat.id),
    onSuccess: () => refetchSeatGroups()
  })

  const { mutate: seatEditMutate } = useMutation({
    mutationFn: ({ seat, dto }: {
      seat: SeatDto;
      dto: SeatEditRequestDto
    }) => seatService.editSeat(siteId, seat.groupId, seat.id, dto),
    onSuccess: () => refetchSeatGroups()
  })

  const { mutate: seatDeleteMutate } = useMutation({
    mutationFn: (seat: SeatDto) => seatService.deleteSeat(siteId, seat.groupId, seat.id),
    onSuccess: () => {
      refetchSeatGroups()
        .then(() => setSelectedSeatId(null));
    }
  })

  const seats = useMemo(() => {
    if (!!seatGroups) {
      return seatGroups.flatMap(it => it.seats).sort();
    }
    return [];
  }, [seatGroups]);

  const groupMap = useMemo<Record<string, SeatGroup>>(() => {
    if (!seatGroups) {
      return {};
    }
    return seatGroups.reduce<Record<string, SeatGroup>>((acc, g) => {
      acc[g.id] = g
      return acc
    }, {})
  }, [seatGroups]);

  const [selectedSeatId, setSelectedSeatId] = useState<string | null>(null)

  return (
    <>
      <div className="flex flex-col h-[calc(100vh-120px)] min-h-[calc(100vh-120px)]">
        {/* ──────────── 상단 3‑패널 영역 ──────────── */}
        <ResizablePanelGroup
          direction="horizontal"
          className="flex-1 rounded-lg border overflow-hidden"
        >
          {/* ───────── 좌측 Seat Groups ───────── */}
          <ResizablePanel defaultSize={22} className="flex flex-col">
            <LeftSidePanel siteId={siteId} seatGroups={seatGroups ?? []} refetch={refetchSeatGroups} />
          </ResizablePanel>

          <ResizableHandle withHandle />

          {/* ───────── 중앙 드래그 캔버스 ───────── */}
          <ResizablePanel defaultSize={56} className="flex flex-col">
            <CenterPanel
              site={data}
              seats={seats}
              seatGroups={seatGroups}
              selectedSeatId={selectedSeatId}
              setSelectedSeatId={setSelectedSeatId}
              groupMap={groupMap}
              refetch={refetchSeatGroups}
            />
          </ResizablePanel>

          <ResizableHandle withHandle />

          {/* ───────── 우측 Seat Properties ───────── */}
          <ResizablePanel defaultSize={22} className="flex flex-col">
            <SeatPropertiesArea
              seat={seats.find(it => it.id === selectedSeatId)}
              seatGroups={seatGroups ?? []}
              onDuplicate={duplicateSeatMutate}
              onEdit={seatEditMutate}
              onDelete={seatDeleteMutate}
            />
          </ResizablePanel>
        </ResizablePanelGroup>
      </div>
    </>
  )
}
