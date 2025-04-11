"use client";

import { Button } from "@/components/ui/button"
import {
  Dialog, DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import * as React from "react";
import { useState } from "react";
import { SeatGroupAddRequest } from "@/lib/types/seat/seat.dto";
import { Dialog as AlertDialog } from "@/lib/dialog/dialog";

export default function SeatGroupAddDialog({
  children,
  open,
  setOpen,
  onSubmit,
  onDelete,
  defaultValues = {
    name: "",
    color: "#FFFFFF",
    displayOrder: 0
  }
}: {
  children: React.ReactNode,
  open: boolean,
  setOpen: React.Dispatch<React.SetStateAction<boolean>>,
  onSubmit: (data: SeatGroupAddRequest) => void,
  onDelete?: () => void,
  defaultValues?: {
    id?: string;
    name: string;
    color: string;
    displayOrder: number;
  }
}) {
  const [color, setColor] = useState(defaultValues.color);
  const [name, setName] = useState(defaultValues.name);

  const handleSubmit = (ev: React.MouseEvent<HTMLButtonElement>) => {
    // ev.preventDefault();
    if (!name) {
      AlertDialog.alert("이름을 입력해 주세요.");
      return;
    }
    onSubmit({
      ...defaultValues,
      name,
      color
    });
  }

  return (
    <>
      <Dialog open={open} onOpenChange={setOpen}>
        <DialogTrigger asChild>
          {children}
        </DialogTrigger>
        <DialogContent className="sm:max-w-[425px]">
          <DialogHeader>
            <DialogTitle>Edit profile</DialogTitle>
            <DialogDescription>
              Make changes to your profile here. Click save when you're done.
            </DialogDescription>
          </DialogHeader>
          <div className="grid gap-4 py-4">
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="name" className="text-right">
                Name
              </Label>
              <Input
                id="name"
                value={name}
                onChange={(ev) => {
                  setName(ev.target.value)
                }}
                className="col-span-3"
              />
            </div>
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="color" className="text-right">
                Color
              </Label>
              <Input
                id="color"
                type="color"
                value={color}
                onChange={(ev) => {
                  setColor(ev.target.value)
                }}
                className="col-span-3"
              />
            </div>
          </div>
          <DialogFooter>
            <Button type="button" onClick={handleSubmit}>Save</Button>
            {
              !!onDelete && <Button type="button" variant="destructive" onClick={handleSubmit}>Delete</Button>
            }
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </>
  )
}