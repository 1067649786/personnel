<template>
  <div>
    <el-container>
      <el-header style="display: flex;justify-content: space-between;align-items: center;padding-left: 0px">
        <el-button size="mini" type="success" @click="loadSalaryCfg" icon="el-icon-refresh"></el-button>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="salaries"
            stripe
            v-loading="tableLoading"
            size="mini"
            style="width: 100%">
            <el-table-column
              width="120"
              prop="eid"
              align="center"
              label="员工编号">
            </el-table-column>
            <el-table-column
              width="200"
              prop="type"
              align="center"
              label="调动/奖惩/培训/调薪">
            </el-table-column>
            <el-table-column
              width="100"
              align="center"
              label="时间">
              <template slot-scope="scope">{{ scope.row.tDate | formatDate}}</template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="remark"
              label="备注">
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        dialogVisible: false,
        tableLoading: false,
        index: 0,
        salaries: [],
        multipleSelection: [],
        salary: {
          id: '',
          eid:'',
          type:'',
          tDate:'',
          remark:''
        }
      };
    },
    methods: {
      deleteAll(){
        this.$confirm('删除[' + this.multipleSelection.length + ']条记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          this.multipleSelection.forEach(row=> {
            ids = ids + row.id + ",";
          })
          this.doDelete(ids);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleEdit(index, row) {
        this.dialogVisible = true;
        row.createDate = new Date(row.createDate);
        delete row.allSalary;
        this.salary = row;
      },
      handleDelete(index, row) {
        this.$confirm('删除[' + row.name + ']账套, 是否继续?', '提示', {
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
        this.deleteRequest("/salary/sob/salary/" + id).then(resp=>{
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;

            _this.loadSalaryCfg();
          }
        });
      },
      next(){
        var _this = this;
        if (this.index == 7) {
          if(this.salary.createDate&&this.salary.basicSalary&&this.salary.trafficSalary&&this.salary.lunchSalary&&this.salary.bonus&&this.salary.pensionBase&&this.salary.pensionPer&&this.salary.medicalBase&&this.salary.medicalPer&&this.salary.accumulationFundBase&&this.salary.accumulationFundPer){
            if (this.salary.id) {//更新
              _this.tableLoading = true;
              this.putRequest("/salary/sob/salary", this.salary).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _
                  _this.dialogVisible = false;
                  _this.index = 0;
                  _this.loadSalaryCfg();
                }
              });
            } else {//添加
              this.$prompt('请输入账套名称', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
              }).then(({value}) => {
                this.salary.name = value;
                this.postRequest("/salary/sob/salary", this.salary).then(resp=> {
                  if (resp && resp.status == 200) {
                    var data = resp.data;

                    _this.dialogVisible = false;
                    _this.index = 0;
                    _this.loadSalaryCfg();
                  }
                });
              }).catch(() => {
              });
            }
          }else{
            this.$message({type: 'error', message: '字段不能为空!'});
          }
        } else {
          this.index++;
        }
      },
      loadSalaryCfg(){
        this.tableLoading = true;
        var _this = this;
        this.getRequest("/statistics/recored/personnel").then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        })
      },
      emptySalary(){
        this.salary = {
          id: '',
          eid:'',
          type:'',
          tDate:'',
          remark:''
        };
        this.index = 0;
      }
    },
    mounted: function () {
      this.loadSalaryCfg();
    }
  };
</script>
<style>
  .el-dialog__body {
    padding-bottom: 10px;
  }
</style>
