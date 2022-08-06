from django.db import models
from django.conf import settings

# Create your models here.

class Article(models.Model):
    # 카테고리 항목
    category_choice = [
        ('', ''),

    ]

    title = models.CharField(max_length=100)
    content = models.TextField()
    # thumbnails = models.ImageField() # 이미지 필드는 나중에 작성하도록 함
    price = models.IntegerField()
    grade = models.IntegerField()
    category = models.CharField(choices=category_choice)
    is_sold = models.BooleanField()
    created_at = models.DateField(auto_now_add=True)
    updated_at = models.DateField(auto_now=True)
    like_article_user = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name='like_articles')


class Comment(models.Model):
    content = models.CharField(max_length=200)
    created_at = models.DateField(auto_now_add=True)
    updated_at = models.DateField(auto_now=True)
