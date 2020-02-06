import request from '@/utils/request';
import { stringify } from 'qs';

// 被代理处理
export async function queryResource(params) {
  return request(`/haoke/house/resources?${stringify(params)}`);
}
