<template>
  <div>
    <el-container>
      <el-header style="display: flex;justify-content: space-between;align-items: center;padding-left: 0px">
        <el-button @click="dialogVisible = true" icon="el-icon-plus" type="primary" size="mini">添加调薪</el-button>
        <el-button size="mini" type="success" @click="loadPerSalary" icon="el-icon-refresh"></el-button>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="perSalary"
            stripe
            v-loading="tableLoading"
            size="mini"
            @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column
              type="selection"
              width="30">
            </el-table-column>
            <el-table-column
              width="90"
              prop="eid"
              label="员工编号">
            </el-table-column>
            <el-table-column
              width="100"
              label="调薪日期">
              <template slot-scope="scope">{{ scope.row.asDate | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="100"
              prop="beforeSalary"
              label="调前薪资">
            </el-table-column>
            <el-table-column
              width="100"
              prop="afterSalary"
              label="调后薪资">
            </el-table-column>
            <el-table-column
              width="200"
              prop="reason"
              label="调薪原因">
            </el-table-column>
            <el-table-column
              width="200"
              prop="remark"
              label="备注">
            </el-table-column>
            <el-table-column label="操作" align="center">
              <el-table-column label="编辑" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑
                  </el-button>
                </template>
              </el-table-column>
              <el-table-column label="删除" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        dialogVisible: false,
        tableLoading: false,
        index: 0,
        multipleSelection: [],
        perSalarise:[],
        perSalary:{
          id:'',
          eid:'',
          asDate:'',
          beforeSalary:'',
          afterSalary:'',
          reason:'',
          remark:''
        }
      };
    },
    methods:{
      loadPerSalary(){
        this.tableLoading = true;
        var _this = this;
        this.getRequest("/personnel/salary/adjust").then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleEdit(index, row) {
        this.dialogVisible = true;
        row.adDate = new Date(row.asDate);
        this.perSalary = row;
      },
      handleDelete(index, row) {
        this.$confirm('删除[' + row.eid + ']调薪, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      doDelete(id){
        var _this = this;
        _this.tableLoading = true;
        this.deleteRequest("/personnel/salary/adjust" + id).then(resp=>{
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;

            _this.loadPerSalary();
          }
        });
      },
      emptySalary(){
        this.perSalary={
          id:'',
          eid:'',
          asDate:'',
          beforeSalary:'',
          afterSalary:'',
          reason:'',
          remark:''
        };
        this.index=0;
      }
    },
    mounted: function () {
      this.loadPerSalary();
    }
  };
</script>
