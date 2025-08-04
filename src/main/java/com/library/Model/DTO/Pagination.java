package com.library.Model.DTO;

public class Pagination {
      private int page;
      private int totalDataCount;

      public Pagination(int page, int totalDataCount) {
            this.page = page;
            this.totalDataCount = totalDataCount;
      }

      public int getPage() {
            return page;
      }

      public void setPage(int page) {
            this.page = page;
      }

      public int getTotalDataCount() {
            return totalDataCount;
      }

      public void setTotalDataCount(int totalDataCount) {
            this.totalDataCount = totalDataCount;
      }
}
