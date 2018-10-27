#coding=utf-8
__date__ = ' 10:04'
__author__ = 'sixkery'

import requests
import json
from pyecharts import Bar
import wordcloud,jieba

'''爬取网易云音乐歌曲「可能否」热门评论'''

# 获取评论数据
def get_data():
    url = 'https://music.163.com/weapi/v1/resource/comments/R_SO_4_38576323?csrf_token='
    headers = {'Host': 'music.163.com',
               'Origin': 'https://music.163.com',
               'Referer': 'https://music.163.com/song?id=38576323',
               'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36'
               }
    data = {'params': '12PSl54ZzScPr+B27R+RJ14gF4YwwNz8YqWdldaCKao1s1/JexmIcnpaQu7oAkXM96vPBpEo42vFSp3BydeeYs6TKv/72oKRITbhg8hUP2vwsNW+hq8VfDvmjcq+ceScl9wEb3Wh6Whnu85Th7jHK4lNNKxNSJakjxuVnNcCDteI76F2xviD4jDcz9upF8CY',
            'encSecKey': '227faa18d4ac5f4dfa07b4f0664bcb181240fcfb74192d7ce86b19ce302c61c8a5f2cbf45fc8874b5d74f0f6320f7681eef36e3f3a4d8349eed908188aae9717dd64f4d678e1d15afb8f06b559ebd51b2bca7b225f274378d89c068e18f7f8d45f7019e6923c2a0da30a4b68ecdfe2d6dcb954c3cfb0ec8812da693944617678'}
    response = requests.post(url,headers=headers,data=data)
    html = json.loads(response.text)
    result = []
    for item in html['hotComments']:
        content = {'user':item['user']['nickname'],
                  'likedCount':item['likedCount'],
                   'content':item['content']
                   }
        result.append(content)
    return result


# 可视化直方图表示
def Histogram(data):
    user_list = [i['user'] for i in data]
    likecount_list = [i['likedCount'] for i in data]
    content_list = [i['content'] for i in data]

    bar = Bar("热评中点赞数示例图")
    bar.add("点赞数", user_list, likecount_list, is_stack=True, mark_line=["min", "max"], mark_point=["average"])
    bar.render()

# 可视化词云图表示
def Wordcloud(data):
    content_list = [i['content'] for i in data]
    content_list.remove(content_list[0])
    print(content_list)
    txt = ''.join(content_list)
    w = wordcloud.WordCloud(font_path='msyh.ttc',width=1000,height=700,max_words=100,
                            stopwords={'春风十里不如你','亲亲','五十里','一百里'})
    w.generate(txt)
    w.to_file('词云图.jpg')

if __name__ == '__main__':
    result = get_data()
    # Histogram(result)
    Wordcloud(result)