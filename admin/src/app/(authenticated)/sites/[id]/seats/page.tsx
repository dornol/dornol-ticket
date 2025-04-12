"use client"

import * as React from "react"
import { useRef, useState } from "react"
import dynamic from "next/dynamic"
import { ResizableHandle, ResizablePanel, ResizablePanelGroup, } from "@/components/ui/resizable"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Checkbox } from "@/components/ui/checkbox"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue, } from "@/components/ui/select"
import { Separator } from "@/components/ui/separator"
import { useParams } from "next/navigation";
import { useMutation, useQuery } from "@tanstack/react-query";
import { SiteDto } from "@/lib/types/site/site.dto";
import siteService from "@/lib/service/site/site-service";
import seatService from "@/lib/service/seat/seat-service";
import { SeatGroup, SeatGroupAddRequest, SeatGroupEditRequest } from "@/lib/types/seat/seat.dto";
import SeatGroupAddDialog from "@/app/(authenticated)/sites/[id]/seats/seat-group-add-dialog";

// ⬇︎ SSR 에러 방지를 위해 클라이언트에서만 로드
const Draggable = dynamic(() => import("react-draggable"), { ssr: false })

interface Seat {
  id: string
  x: number
  y: number
  group: string
  disabled?: boolean
}

const GROUPS = [
  { id: "vip", label: "VIP", color: "bg-blue-400", dot: "bg-blue-600" },
  { id: "r", label: "R", color: "bg-green-300", dot: "bg-green-600" },
  { id: "s", label: "S", color: "bg-orange-300", dot: "bg-orange-600" },
] as const

const groupColorMap = GROUPS.reduce<Record<string, string>>((acc, g) => {
  acc[g.id] = g.color
  return acc
}, {})

const queryKey = 'site';
const seatGroupQueryKey = 'seat-group'


export default function SeatsPage() {
  const { id: siteId }: { id: string } = useParams();
  const [open, setOpen] = useState(false);
  const [editOpenId, setEditOpenId] = useState<string | null>(null);

  const { data } = useQuery<SiteDto>({
    queryKey: [queryKey, siteId],
    queryFn: () => siteService.get(siteId)
  })

  const { data: seatGroups, refetch: refetchSeatGroups } = useQuery<SeatGroup[]>({
    queryKey: [seatGroupQueryKey, siteId],
    queryFn: () => seatService.getSeatGroups(siteId)
  })

  const { mutate: seatGroupAddMutate } = useMutation({
    mutationFn: (payload: SeatGroupAddRequest) => {
      return seatService.addSeatGroup(siteId, payload)
    },
    onSuccess: () => {
      refetchSeatGroups()
        .then(() => setOpen(false));
    }
  })

  const { mutate: seatGroupEditMutate } = useMutation({
    mutationFn: (payload: SeatGroupEditRequest) => {
      return seatService.editSeatGroup(siteId, payload.id, {
        name: payload.name,
        color: payload.color
      })
    },
    onSuccess: () => {
      refetchSeatGroups()
        .then(() => setEditOpenId(null));
    }
  })

  const { mutate: seatGroupDeleteMutate } = useMutation({
    mutationFn: (seatGroupId: string) => seatService.deleteSeatGroup(siteId, seatGroupId),
    onSuccess: () => {
      refetchSeatGroups()
        .then(() => setEditOpenId(null));
    }
  })

  const [seats, setSeats] = React.useState<Seat[]>([
    { id: "A1", x: 0, y: 0, group: "vip" },
    { id: "A2", x: 40, y: 0, group: "vip" },
    { id: "B1", x: 0, y: 40, group: "r" },
    { id: "B2", x: 40, y: 40, group: "r" },
  ])
  const nodeRef = useRef<HTMLDivElement>(null)

  const [selectedSeatId, setSelectedSeatId] = React.useState<string | null>(null)

  /* ───────── drag stop → 상태 + 백엔드 저장 ───────── */
  const handleDragStop = (id: string, x: number, y: number) => {
    setSeats((prev) => prev.map((s) => (s.id === id ? { ...s, x, y } : s)))

    // TODO: ① 여기서 API 호출로 좌표를 영속화
    // await api.saveSeatPosition(id, x, y)
  }

  const selectedSeat = seats.find((s) => s.id === selectedSeatId) || null

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
            <div className="flex-1 overflow-y-auto p-6 space-y-4">
              <h2 className="text-2xl font-semibold">Seat Groups</h2>
              {
                seatGroups?.map(seatGroup => (
                  <SeatGroupAddDialog
                    open={editOpenId === seatGroup.id}
                    setOpen={(open) => {
                      if (open) {
                        setEditOpenId(seatGroup.id);
                      } else {
                        setEditOpenId(null);
                      }
                    }}
                    key={seatGroup.id}
                    onSubmit={(data) => {
                      seatGroupEditMutate({ ...data, id: seatGroup.id })
                    }}
                    onDelete={seatGroupDeleteMutate}
                    defaultValues={seatGroup}
                  >
                    <button
                      className={`flex items-center gap-3 w-full h-14 px-4 rounded-md border shadow-sm hover:bg-muted/50 transition`}
                      style={{
                        backgroundColor: seatGroup.color
                      }}
                    >
                      <span className={`w-6 h-6 rounded`}/>
                      <span className="text-lg font-medium">{seatGroup.name}</span>
                    </button>
                  </SeatGroupAddDialog>
                ))

              }

              <Separator />

              <SeatGroupAddDialog open={open} setOpen={setOpen} onSubmit={seatGroupAddMutate}>
                <Button variant="outline" className="w-full h-14 gap-3">
                  <span className="text-xl">＋</span> Add Group
                </Button>
              </SeatGroupAddDialog>
            </div>
          </ResizablePanel>

          <ResizableHandle withHandle />

          {/* ───────── 중앙 드래그 캔버스 ───────── */}
          <ResizablePanel defaultSize={56} className="flex flex-col">
            {/* 👇 이 div는 오직 스크롤만 담당 */}
            <div className="relative flex-1 overflow-auto">
              {/* 👇 이 div에 배경 이미지 적용 → 좌석과 함께 스크롤됨 */}
              <div
                className="relative w-[600px] h-[600px] bg-no-repeat bg-top bg-contain"
                style={{
                  backgroundImage: `url(${data?.seatingMapLocation})`,
                  backgroundSize: '100% 100%',
                }}
              >
                {/* 좌석들 */}
                {seats.map((seat) => (
                  <Draggable
                    key={seat.id}
                    nodeRef={nodeRef as React.RefObject<HTMLDivElement>}
                    grid={[1, 1]}
                    bounds="parent"
                    position={{ x: seat.x, y: seat.y }}
                    onStop={(_, data) => handleDragStop(seat.id, data.x, data.y)}
                    onStart={() => setSelectedSeatId(seat.id)}
                  >
                    <div
                      ref={nodeRef}
                      className={`w-8 h-8 border border-black/30 rounded-sm shadow-sm cursor-move ${groupColorMap[seat.group]} ${
                        seat.disabled ? "opacity-40" : ""
                      }`}
                    />
                  </Draggable>
                ))}
              </div>
            </div>
          </ResizablePanel>

          <ResizableHandle withHandle />

          {/* ───────── 우측 Seat Properties ───────── */}
          <ResizablePanel defaultSize={22} className="flex flex-col">
            <div className="flex-1 overflow-y-auto p-6 space-y-6">
              <h2 className="text-2xl font-semibold">Seat Properties</h2>

              {selectedSeat ? (
                <>
                  {/* ID */}
                  <div className="space-y-2">
                    <label className="text-sm font-medium">ID</label>
                    <Input
                      value={selectedSeat.id}
                      onChange={(e) =>
                        setSeats((prev) =>
                          prev.map((s) =>
                            s.id === selectedSeat.id
                              ? { ...s, id: e.target.value }
                              : s,
                          ),
                        )
                      }
                    />
                  </div>

                  {/* Group */}
                  <div className="space-y-2">
                    <label className="text-sm font-medium">Group</label>
                    <Select
                      value={selectedSeat.group}
                      onValueChange={(value) =>
                        setSeats((prev) =>
                          prev.map((s) =>
                            s.id === selectedSeat.id ? { ...s, group: value } : s,
                          ),
                        )
                      }
                    >
                      <SelectTrigger>
                        <SelectValue placeholder="Select group" />
                      </SelectTrigger>
                      <SelectContent>
                        {GROUPS.map((g) => (
                          <SelectItem key={g.id} value={g.id}>
                            {g.label}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  {/* Disabled */}
                  <div className="flex items-center gap-3">
                    <Checkbox
                      id="disabled"
                      checked={selectedSeat.disabled ?? false}
                      onCheckedChange={(checked) =>
                        setSeats((prev) =>
                          prev.map((s) =>
                            s.id === selectedSeat.id
                              ? { ...s, disabled: checked as boolean }
                              : s,
                          ),
                        )
                      }
                    />
                    <label htmlFor="disabled" className="text-sm">
                      Disabled
                    </label>
                  </div>

                  {/* Delete / Duplicate */}
                  <div className="flex gap-3 pt-2">
                    <Button
                      variant="destructive"
                      className="flex-1"
                      onClick={() =>
                        setSeats((prev) => prev.filter((s) => s.id !== selectedSeat.id))
                      }
                    >
                      Delete
                    </Button>
                    <Button
                      variant="outline"
                      className="flex-1"
                      onClick={() =>
                        setSeats((prev) => [
                          ...prev,
                          {
                            ...selectedSeat,
                            id: selectedSeat.id + "-copy",
                            x: selectedSeat.x + 40,
                          },
                        ])
                      }
                    >
                      Duplicate
                    </Button>
                  </div>
                </>
              ) : (
                <p className="text-sm text-muted-foreground">
                  Click a seat to edit.
                </p>
              )}
            </div>
          </ResizablePanel>
        </ResizablePanelGroup>
      </div>
    </>
  )
}
