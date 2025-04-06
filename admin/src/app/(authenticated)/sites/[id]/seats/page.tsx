"use client";

import * as React from "react";
import {
  ResizableHandle,
  ResizablePanel,
  ResizablePanelGroup,
} from "@/components/ui/resizable"
import SeatsFormMenuBar from "@/app/(authenticated)/sites/[id]/seats/seats-form-menu-bar";

export default function SeatsPage() {
  return (
    <div className="min-h-[calc(100vh-120px)] max-h-[calc(100vh-120px)] h-[calc(100vh-120px)] flex flex-col">
      <SeatsFormMenuBar />

      <ResizablePanelGroup
        direction="horizontal"
        className="h-full max-w-full rounded-lg border md:min-w-[450px]"
      >
        <ResizablePanel defaultSize={25}>
          <div className="flex h-full items-center justify-center p-6">
            <span className="font-semibold">Sidebar</span>
          </div>
        </ResizablePanel>
        <ResizableHandle withHandle />
        <ResizablePanel defaultSize={75}>
          <div className="flex h-full items-center justify-center p-6">
            <span className="font-semibold">Content</span>
          </div>
        </ResizablePanel>
        <ResizableHandle withHandle />
        <ResizablePanel defaultSize={25}>
          <div className="flex h-full items-center justify-center p-6">
            <span className="font-semibold">Sidebar</span>
          </div>
        </ResizablePanel>
      </ResizablePanelGroup>
    </div>
  )
}