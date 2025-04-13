import SeatGroupAddDialog from "@/app/(authenticated)/sites/[id]/seats/seat-group-add-dialog";
import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";
import * as React from "react";
import { useState } from "react";
import { QueryObserverResult, useMutation } from "@tanstack/react-query";
import { SeatGroup, SeatGroupAddRequest, SeatGroupEditRequest } from "@/lib/types/seat/seat.dto";
import seatService from "@/lib/service/seat/seat-service";

export default function LeftSidePanel({
  siteId,
  seatGroups,
  refetch
}: {
  siteId: string;
  seatGroups: SeatGroup[];
  refetch: () => Promise<QueryObserverResult<SeatGroup[], Error>>;
}) {
  const [open, setOpen] = useState(false);
  const [editOpenId, setEditOpenId] = useState<string | null>(null);

  const { mutate: seatGroupAddMutate } = useMutation({
    mutationFn: (payload: SeatGroupAddRequest) => {
      return seatService.addSeatGroup(siteId, payload)
    },
    onSuccess: () => {
      refetch()
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
      refetch()
        .then(() => setEditOpenId(null));
    }
  })

  const { mutate: seatGroupDeleteMutate } = useMutation({
    mutationFn: (seatGroupId: string) => seatService.deleteSeatGroup(siteId, seatGroupId),
    onSuccess: () => {
      refetch()
        .then(() => setEditOpenId(null));
    }
  })

  return (
    <>
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
                <span className={`w-6 h-6 rounded`} />
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
    </>
  )
}