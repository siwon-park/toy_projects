from django.db import models
from django.conf import settings

# Create your models here.
class Article(models.Model):
    # 카테고리 항목
    category_choice = [
        ('', ''),

    ]
    grade_choice = [
        (1, 'F급'),
        (2, 'D급'),
        (3, 'C급'),
        (4, 'B급'),
        (5, 'A급'),
        (6, 'S급')
    ]

    title = models.CharField(max_length=100) # CharField에 max_length는 필수값
    content = models.TextField()
    # thumbnails = models.ImageField() # 이미지 필드는 나중에 작성하도록 함
    price = models.IntegerField()
    grade = models.IntegerField(choices=grade_choice)
    category = models.CharField(choices=category_choice, max_length=30)
    is_sold = models.BooleanField()
    created_at = models.DateField(auto_now_add=True)
    updated_at = models.DateField(auto_now=True)
    like_article_user = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name='like_articles')


class Comment(models.Model):
    content = models.CharField(max_length=200)
    created_at = models.DateField(auto_now_add=True)
    updated_at = models.DateField(auto_now=True)
