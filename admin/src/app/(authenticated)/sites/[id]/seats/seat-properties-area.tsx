"use client";

import { SeatDto, SeatGroup } from "@/lib/types/seat/seat.dto";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Button } from "@/components/ui/button";
import * as React from "react";
import { useEffect, useState } from "react";
import { SeatEditRequestDto } from "@/lib/types/site/site.dto";

export default function SeatPropertiesArea({
  seat,
  seatGroups,
  onDuplicate,
  onEdit,
  onDelete,
}: {
  seat?: SeatDto | null,
  seatGroups: SeatGroup[],
  onDuplicate: (seat: SeatDto) => void,
  onEdit: ({seat, dto}: {seat: SeatDto, dto: SeatEditRequestDto}) => void,
  onDelete: (seat: SeatDto) => void,
}) {
  const [name, setName] = useState<string>(seat?.name ?? "")
  const [groupId, setGroupId] = useState<string>(seat?.groupId ?? "");

  useEffect(() => {
    setName(seat?.name ?? "")
    setGroupId(seat?.groupId ?? "")
  }, [seat]);

  return (
    <>
      <div className="flex-1 overflow-y-auto p-6 space-y-6">
        <h2 className="text-2xl font-semibold">Seat Properties</h2>

        {seat ? (
          <>
            {/* ID */}
            <div className="space-y-2">
              <label className="text-sm font-medium">Name</label>
              <Input
                value={name}
                onChange={e => setName(e.target.value)}
              />
            </div>

            {/* Group */}
            <div className="space-y-2">
              <label className="text-sm font-medium">Group</label>
              <Select
                value={groupId}
                onValueChange={value => setGroupId(value)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select group" />
                </SelectTrigger>
                <SelectContent>
                  {seatGroups?.map((g) => (
                    <SelectItem key={g.id} value={g.id}>
                      {g.name}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {/* Delete / Duplicate */}
            <div className="flex gap-3 pt-2">
              <Button
                variant="destructive"
                className="flex-1"
                onClick={() => {onDelete(seat)}}
              >
                Delete
              </Button>
              <Button
                variant="outline"
                className="flex-1"
                onClick={() => onDuplicate(seat)}
              >
                Duplicate
              </Button>
              <Button
                variant="outline"
                className="flex-1"
                onClick={() => onEdit({seat, dto: {name, groupId}})}
              >
                Edit
              </Button>
            </div>
          </>
        ) : (
          <p className="text-sm text-muted-foreground">
            Click a seat to edit.
          </p>
        )}
      </div>
    </>
  )
}