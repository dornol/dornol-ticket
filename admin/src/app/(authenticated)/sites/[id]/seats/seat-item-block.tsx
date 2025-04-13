"use client";

import * as React from "react";
import { RefObject, useRef, useState } from "react";
import { SeatDto } from "@/lib/types/seat/seat.dto";
import dynamic from "next/dynamic";

// ⬇︎ SSR 에러 방지를 위해 클라이언트에서만 로드
const Draggable = dynamic(() => import("react-draggable"), { ssr: false })

export default function SeatItemBlock({
  seat,
  color,
  onSelect,
  onMove,
  active = false
}: {
  seat: SeatDto | null;
  color: string;
  onSelect: (seat: SeatDto) => void;
  onMove: (seat: SeatDto, x: number, y: number) => void;
  active: boolean;
}) {
  const nodeRef = useRef<HTMLDivElement>(null);  // 각 컴포넌트마다 독자적인 ref 생성
  const [seatState, setSeatState] = useState<SeatDto | null>(seat);

  const handleDragStop = (seat: SeatDto, x: number, y: number) => {
    setSeatState({
      ...seat,
      offset: {x, y}
    })
    onMove(seat, x, y);
  }

  if (!seatState) {
    return <></>;
  }

  return (
    <>
      <Draggable
        key={seatState.id}
        nodeRef={nodeRef as RefObject<HTMLDivElement>}
        grid={[1, 1]}
        bounds="parent"
        defaultPosition={{ x: seatState.offset.x, y: seatState.offset.y }}
        onStop={(_, data) => handleDragStop(seatState, data.x, data.y)}
        onStart={() => onSelect(seatState)}
      >

        <div
          ref={nodeRef}
          className={`w-8 h-8 border border-black/30 rounded-sm shadow-sm cursor-move ${active ? 'outline-4 outline-chart-4' : ''}`}
          style={{
            backgroundColor: color,
            position: "absolute"
          }}
          title={seat?.name ?? ""}
        />
      </Draggable>
    </>
  )
}