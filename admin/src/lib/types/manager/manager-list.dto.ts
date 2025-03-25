import { ManagerRole } from "@/lib/types/auth/auth";
import { Company } from "@/lib/types/company/company.dto";

export interface ManagerListDto {
  id: string;
  username: string;
  name: string;
  phone: string;
  email: string;
  managerRole: ManagerRole;
  approval: Approval;
  company: Company;
}

export interface Approval {
  approved: boolean;
  approvedDate?: Date | null;
  approvedBy?: number | null;
}