"use client";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { SearchOption, SearchOptions } from "@/lib/types/search/search.dto";
import SearchSelectBox from "@/components/table/searchbox/search-select-box";
import SearchCheckbox from "@/components/table/searchbox/search-checkbox";
import React, { useState } from "react";
import {
  DropdownMenu,
  DropdownMenuCheckboxItem,
  DropdownMenuContent,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

function SearchOptionBox({ searchOption }: { searchOption: SearchOption }) {
  return (
    <>
      {searchOption.type === "select" && SearchSelectBox(searchOption)}
      {searchOption.type === "checkbox" && SearchCheckbox(searchOption)}
    </>
  )
}

export default function SearchBox({
  searchOptions,
  onSearch
}: {
  onSearch: (search: Record<string, string>) => void,
  searchOptions: SearchOptions
}) {
  const [checkedSearchFields, setCheckedSearchFields] = useState<string[]>(searchOptions.searchFields.map(it => it.value));

  const handleSubmit = (ev: React.FormEvent) => {
    ev.preventDefault();
    const formData = new FormData(ev.target as HTMLFormElement);

    const newSearch: Record<string, string> = {};
    formData.forEach((value, key) => {
      newSearch[key] = value as string;
    })
    newSearch.searchFields = checkedSearchFields.join(",");

    onSearch(newSearch);
  }

  return (
    <form onSubmit={handleSubmit}>
      <div className="flex w-full items-center space-x-2 ml-auto">
        {
          searchOptions.searchOptions.map((searchOption) => <SearchOptionBox key={`search-box-${searchOption.name}`}
                                                                             searchOption={searchOption} />)
        }
        <div
          className="border-input flex h-9 w-full min-w-0 rounded-md border bg-transparent text-base shadow-xs outline-none items-center overflow-hidden"
        >
          <DropdownMenu modal={false}>
            <DropdownMenuTrigger asChild>
              <Button
                variant="ghost"
                className="border-0 focus-visible:outline-none focus-visible:ring-0 focus-visible:border-none rounded-none"
              >
                Filter By
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent className="w-56">
              <DropdownMenuLabel>Filter By Fields</DropdownMenuLabel>
              <DropdownMenuSeparator />
              {
                searchOptions.searchFields.map((searchField) => (
                  <DropdownMenuCheckboxItem
                    key={`searchField-${searchField.value}`} onSelect={(e) => e.preventDefault()}
                    checked={checkedSearchFields.some(it => it === searchField.value)}
                    onCheckedChange={(checked) => {
                      setCheckedSearchFields((state) => {
                        if (checked) {
                          return [...state, searchField.value];
                        } else {
                          return state.filter(it => it !== searchField.value);
                        }
                      })
                    }}
                  >
                    {searchField.title}
                  </DropdownMenuCheckboxItem>
                ))
              }
            </DropdownMenuContent>
          </DropdownMenu>
          <Input type="text" placeholder="Search query" name="searchText"
                 className="border-0 focus-visible:outline-none focus-visible:ring-0 focus-visible:border-none rounded-none" />
        </div>
        <Button type="submit" variant="outline">Search</Button>
      </div>
    </form>
  )
}