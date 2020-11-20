import defaultSettings from '@/settings'

const title = defaultSettings.title || '末端供应链管理'
import i18n from "@/lang";
export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    pageTitle = i18n.t("route."+pageTitle)
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
