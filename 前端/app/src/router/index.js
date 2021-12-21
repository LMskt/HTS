import Vue from 'vue'
import VueRouter from 'vue-router'
import homepage from "@/views/homepage/homepage";
import parents from "@/views/parents/parents";
import teachers from "@/views/teachers/teachers";
import admin from "@/views/admin/admin";
import relationship from "@/views/parents/chil/relationship";
import detail from "@/views/parents/chil/detail";
import task from "@/views/common/task";
import addstudent from "@/views/parents/chil/addstudent";
import mytask from "@/views/parents/chil/mytask";
import taskhome from "@/views/common/taskhome";
import addhometask from "@/views/common/addhometask";
import parnotice from "@/views/parents/chil/parnotice";
import teastudent from "@/views/teachers/chil/teastudent";
import teadetail from "@/views/teachers/chil/teadetail";
import teachertaskhome from "@/views/teachers/chil/teachertaskhome";
import schtask from "@/views/teachers/chil/schtask";
import schtask2 from "@/views/teachers/chil/schtask2";
import teanotice from "@/views/teachers/chil/teanotice";
import addnotice from "@/views/teachers/chil/addnotice";
import addschooltask from "@/views/common/addschooltask";
import astudent from "@/views/admin/chil/astudent";
import ateacher from "@/views/admin/chil/ateacher";
import aparent from "@/views/admin/chil/aparent";
import addstudenta from "@/views/admin/chil/addstudenta";
import addteachera from "@/views/admin/chil/addteachera";
import addparenta from "@/views/admin/chil/addparenta";


Vue.use(VueRouter)

const routes = [
  {
    path:'',
    redirect:'/homepage'
  },
  {
    path: '/homepage',
    name:'homepage',
    component:homepage
  },
  {
    path: '/parents',
    name:'parents',
    component:parents,
    children:[
      {
        path: '/parents',
        redirect:'/relationship'
      },
      {
        path:'/relationship',
        name:'relationship',
        component:relationship
      },
      {
        path:'/detail',
        name:'detail',
        component:detail
      },
      {
        path: '/task',
        name: 'task',
        component: task
      },
      {
        path: '/addstudent',
        name:'addstudent',
        component: addstudent
      },
      {
        path: '/mytask',
        name:'mytask',
        component: mytask
      },
      {
        path:'/taskhome',
        name:'taskhome',
        component: taskhome
      },
      {
        path: '/addhometask',
        name:'addhometask',
        component: addhometask
      },
      {
        path: '/parnotice',
        name:'parnotice',
        component: parnotice
      }

    ]
  },
  {
    path: '/teachers',
    name: 'teachers',
    component: teachers,
    children: [
      {
        path: '/teachers',
        redirect: '/teastudent'
      },
      {
        path: '/teastudent',
        name: 'teastudent',
        component: teastudent
      },
      {
        path: '/teadetail',
        name:'teadetail',
        component: teadetail
      },
      {
        path:'/teachertaskhome',
        name: 'teachertaskhome',
        component: teachertaskhome
      },
      {
        path: '/schtask',
        name: 'schtask',
        component: schtask
      },
      {
        path: '/sctask2',
        name:'sctask2',
        component: schtask2
      },
      {
        path: '/teanotice',
        name: 'teanotice',
        component: teanotice
      },
      {
        path: '/addnotice',
        name:'addnotice',
        component: addnotice
      },
      {
        path: '/addschooltask',
        name: 'addschooltask',
        component: addschooltask
      }
    ]
  },
  {
    path: '/admin',
    name:'admin',
    component: admin,
    children: [
      {
        path: '/admin',
        redirect: '/astudent'
      },
      {
        path: '/astudent',
        name: 'astudent',
        component: astudent
      },
      {
        path: '/ateacher',
        name: 'ateacher',
        component: ateacher
      },
      {
        path: '/aparent',
        name:'aparent',
        component: aparent
      },
      {
        path: '/addstudenta',
        name: 'addstudenta',
        component: addstudenta
      },
      {
        path:'/addteachera',
        name:'addteachera',
        component: addteachera
      },
      {
        path: '/addparenta',
        name:'addparenta',
        component: addparenta
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
