package com.library.Model.DTO;

import java.util.List;

public class ApiDataResponse<T> {
      private List<T> rows;
      private Pagination pagination;

      public ApiDataResponse(List<T> rows, Pagination pagination) {
            this.rows = rows;
            this.pagination = pagination;
      }

      public List<T> getRows() {
            return rows;
      }

      public void setRows(List<T> rows) {
            this.rows = rows;
      }

      public Pagination getPagination() {
            return pagination;
      }

      public void setPagination(Pagination pagination) {
            this.pagination = pagination;
      }
}
