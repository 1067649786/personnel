<template>
  <div>
    <el-container>
      <el-header style="display: flex;justify-content: space-between;align-items: center;padding-left: 0px">
        <el-button @click="dialogVisible = true" icon="el-icon-plus" type="primary" size="mini">添加培训</el-button>
        <el-button size="mini" type="success" @click="loadTrainList" icon="el-icon-refresh"></el-button>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px;">
        <div>
          <el-table
            :data="trains"
            stripe
            v-loading="tableLoading"
            size="mini"
            @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column type="selection" width="30"></el-table-column>
            <el-table-column width="90" prop="eid" label="员工编号" align="center"></el-table-column>
            <!--<el-table-column width="90" prop="workID" label="工号" align="center"></el-table-column>-->
            <el-table-column width="120" label="培训日期" align="center">
              <template slot-scope="scope">{{scope.row.trainDate | formatDate}}</template>
            </el-table-column>
            <el-table-column width="400" prop="trainContent" label="培训内容" align="center"></el-table-column>
            <el-table-column width="400" prop="remark" label="备注" align="center"></el-table-column>
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
        <div style="text-align: left;margin-top: 10px" v-if="trains!=0">
          <el-button type="danger" round size="mini" :disabled="multipleSelection.length==0" @click="deleteAll">批量删除
          </el-button>
        </div>
      </el-main>
    </el-container>
    <div style="text-align: left">
      <el-dialog
        title="添加员工培训信息"
        :visible.sync="dialogVisible"
        width="50%"
        @close="emptyTrain"
        :close-on-click-modal="false">
        <div style="display: flex;justify-content: flex-start;">
          <el-steps :active="index" direction="vertical">
            <el-step title="员工编号" size="mini"></el-step>
            <!--<el-step title="员工号"></el-step>-->
            <el-step title="培训日期"></el-step>
            <el-step title="培训内容"></el-step>
            <el-step title="备注"></el-step>
          </el-steps>
          <div style="margin-left: 30px;display: flex;justify-content: center;align-items: center;width: 80%;">
            <div v-show="index==0">
              员工编号：
              <el-input
                placeholder="请输入员工编号..."
                size="mini"
                style="width: 200px"
                @keyup.enter.native="next"
                v-model="empTrain.eid">
              </el-input>
            </div>
            <!--<div v-show="index==1">
              员工号：
              <el-input
                placeholder="请输入员工号..."
                @keyup.enter.native="next"
                size="mini"
                style="width: 200px"
                v-model="empTrain.workID">
              </el-input>
            </div>-->
            <div v-show="index==1">
              培训日期：
              <el-date-picker
                v-model="empTrain.trainDate"
                type="date"
                size="mini"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width: 200px;"
                placeholder="选择日期">
              </el-date-picker>
            </div>
            <div v-show="index==2">
              培训内容:
              <el-input
                type="textarea"
                size="mini"
                style="width: 200px;"
                :autosize="{ minRows: 5, maxRows: 10}"
                placeholder="请输入培训内容..."
                v-model="empTrain.trainContent">
              </el-input>
            </div>
            <div v-show="index==3">
              备注:
              <el-input
                type="textarea"
                size="mini"
                style="width: 200px;"
                :autosize="{ minRows: 5, maxRows: 10}"
                placeholder="请输入备注内容..."
                v-model="empTrain.remark">
              </el-input>
            </div>
          </div>
        </div>
        <div style="display: flex;align-items: center;justify-content: center;padding: 0px;margin: 0px;">
          <el-button round size="mini" v-if="index!=0" @click="index--">上一步</el-button>
          <el-button type="primary" round size="mini" @click="next" v-text="index==3?'完成':'下一步'"></el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        dialogVisible:false,
        trains:[],
        index:0,
        tableLoading:false,
        multipleSelection:[],
        empTrain:{
          id:'',
          eid:'',
          trainDate:'',
          trainContent:'',
          remark:''
        }
      };
    },
    methods:{
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      loadTrainList(){
        this.tableLoading = true;
        var _this = this;
        this.getRequest("/personnel/train/trains").then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.trains = resp.data;
          }
        })
      },
      handleEdit(index, row) {
        this.dialogVisible = true;
        row.trainDate = new Date(row.trainDate);
        this.empTrain = row;
      },
      handleDelete(index, row) {
        this.$confirm('删除[' + row.name + ']的培训信息, 是否继续?', '提示', {
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
        this.deleteRequest("/personnel/train/trains/" + id).then(resp=>{
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;

            _this.loadTrainList();
          }
        });
      },
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
      emptyTrain(){
        this.empTrain={
          id:'',
          eid:'',
          trainDate:'',
          trainContent:'',
          remark:''
        };
        this.index = 0;
      },
      next(){
        var _this = this;
        if (this.index == 3) {
          if(this.empTrain.eid&&this.empTrain.trainDate&&this.empTrain.trainContent&&this.empTrain.remark){
            if (this.empTrain.id) {//更新
              _this.tableLoading = true;
              this.putRequest("/personnel/train/trains", this.empTrain).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _
                  _this.dialogVisible = false;
                  _this.index = 0;
                  _this.loadTrainList();
                }
              });
            } else {//添加
                this.postRequest("/personnel/train/trains", this.empTrain).then(resp=> {
                  if (resp && resp.status == 200) {
                    var data = resp.data;

                    _this.dialogVisible = false;
                    _this.index = 0;
                    _this.loadTrainList();
                  }
                });
            }
          }else{
            this.$message({type: 'error', message: '字段不能为空!'});
          }
        } else {
          this.index++;
        }
      },
      mounted: function () {
        this.loadSalaryCfg();
      }
    }
  }
</script>
