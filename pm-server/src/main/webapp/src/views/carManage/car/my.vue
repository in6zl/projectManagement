<template>
  <div class="main">
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item :label="$t('car.carNo')" prop="carNo">
        <el-input
          v-model="queryParams.carNo"
          :placeholder="$t('main.placeInsert')"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="$t('car.type')" prop="type">
        <el-input
          v-model="queryParams.type"
          :placeholder="$t('main.placeInsert')"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="$t('car.inUse')" prop="inUse">
        <el-input
          v-model="queryParams.inUse"
          :placeholder="$t('main.placeInsert')"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="$t('car.isOpen')" prop="isOpen">
        <el-input
          v-model="queryParams.isOpen"
          :placeholder="$t('main.placeInsert')"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item style="float:right">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column :label="$t('table.serial')" align="center" prop="carId" width="100" />
      <el-table-column
        :label="$t('car.carNo')"
        align="center"
        prop="carNo"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        :label="$t('car.driverName')"
        align="center"
        prop="driverName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        :label="$t('car.driverPhone')"
        align="center"
        prop="driverPhone"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        :label="$t('car.type')"
        align="center"
        prop="type"
        :formatter="typeFormat"
        width="100"
      />
      <el-table-column
        :label="$t('car.isOpen')"
        align="center"
        prop="isOpen"
        :formatter="statusFormat"
        width="100"
      />
      <el-table-column
        :label="$t('car.inUse')"
        align="center"
        prop="inUse"
        :formatter="statusFormat"
        width="100"
      />
      <!--<el-table-column label="创建时间" align="center" prop="createTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- <div class="paging">
      <div>显示第 1 至 10 项结果，共{{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="total"
          background
          small
        ></el-pagination>
      </div>
    </div>-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNo"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { addCar, updateCar, getCar, listCar, pageCar, deleteCar } from '@/api/car'

export default {
  name: 'Car',
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 公告表格数据
      noticeList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 类型数据字典
      statusOptions: [],
      // 状态数据字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageParam: {
          pageNo: 0,
          pageSize: 10
        },
        noticeTitle: undefined,
        createBy: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        noticeTitle: [
          { required: true, message: '公告标题不能为空', trigger: 'blur' }
        ],
        noticeType: [
          { required: true, message: '公告类型不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
    /* this.getDicts("sys_notice_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_notice_type").then(response => {
      this.typeOptions = response.data;
    });*/
  },
  methods: {
    /** 查询公告列表 */
    getList() {
      this.loading = true
      pageCar(this.queryParams).then(response => {
        this.noticeList = response.data.content
        this.total = response.data.totalElements
        this.loading = false
      })
    },
    // 公告状态字典翻译
    statusFormat(row, column) {
      // return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 公告状态字典翻译
    typeFormat(row, column) {
      // return this.selectDictLabel(this.typeOptions, row.noticeType);
    },
    // 取消按钮
    cancel() {
      /* this.open = false;
      this.reset();*/
    },
    // 表单重置
    reset() {
      /* this.form = {
        noticeId: undefined,
        noticeTitle: undefined,
        noticeType: undefined,
        noticeContent: undefined,
        status: "0"
      };
      this.resetForm("form");*/
    },
    /** 搜索按钮操作 */
    handleQuery() {
      /* this.queryParams.pageNo = 1;
      this.getList();*/
    },
    /** 重置按钮操作 */
    resetQuery() {
      /* this.resetForm("queryForm");
      this.handleQuery();*/
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      /* this.ids = selection.map(item => item.noticeId)
      this.single = selection.length!=1
      this.multiple = !selection.length*/
    },
    /** 新增按钮操作 */
    handleAdd() {
      /* this.reset();
      this.open = true;
      this.title = "添加公告";*/
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      /* this.reset();
      const noticeId = row.noticeId || this.ids
      getNotice(noticeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公告";
      });*/
    },
    /** 提交按钮 */
    submitForm: function() {
      /* this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.noticeId != undefined) {
            updateNotice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addNotice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });*/
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      /* const noticeIds = row.noticeId || this.ids
    this.$confirm('确定删除此数据吗公告编号为"' + noticeIds + '"的数据项?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(function() {
      return delNotice(noticeIds)
    }).then(() => {
      this.getList()
      this.msgSuccess('删除成功')
    }).catch(function() {})*/
    }
  }
}
</script>
